(ns br.com.reactive-poc.rest-service
  (:require 
    [clojure.tools.logging :as log]
    [immutant.web             :as web]
    [immutant.web.async       :as async]
    [immutant.web.middleware  :as web-middleware]
    [compojure.route          :as route]
    [clojure.data.json        :as json]
    [environ.core             :refer (env)]
    [compojure.core           :refer (ANY GET defroutes)]
    [ring.util.response       :refer (response redirect content-type)]
    [br.com.reactive-poc.distance :refer :all])
  (:gen-class))

(defn parse-response [msg]
  (if (nil? msg)
    ""
    (json/write-str msg :escape-unicode true))
)

(defn parse-exception
  "Faz o parsing do erro"
  ([err message]
    (let [
          result-error (assoc {} :message message :error (str err))
         ]
      (log/error "Bad request - wrong message: " message " => " (str err))
      (parse-response result-error)
    )
  )
)

(defn parse-request
  "Verifica se o request é válido e faz o parsing do body"
  ([msg]
    {:pre [every? (every-pred #(not (empty? %))) [msg]]}
    (try
      (let [
        result {}
        request-msg (json/read-str msg :key-fn keyword)
        ]
        (parse-response (assoc result 
                               :result 
                               (distance {:source/lng (:source-lng request-msg), :source/lat (:source-lat request-msg)} 
                                         {:to/lng (:to-lng request-msg), :to/lat (:to-lat request-msg)})))
      )
      (catch java.lang.Exception ex (parse-exception ex msg)))
  )
)

(def websocket-callbacks
  "WebSocket callback functions"
  {:on-open   (fn [channel]
    (async/send! channel "Ready to reverse your messages!"))
  :on-close   (fn [channel {:keys [code reason]}]
    (println "close code:" code "reason:" reason))
  :on-message (fn [ch m]
    (async/send! ch (parse-request m)))
  }
)

(defroutes routes
  (GET "/calculate-distance/:source-lat/:source-lng/:to-lat/:to-lng" 
       [source-lat source-lng to-lat to-lng]
       (response (str (distance {:source/lng (Double/parseDouble source-lng), :source/lat (Double/parseDouble source-lat)} 
                                {:to/lng (Double/parseDouble to-lng), :to/lat (Double/parseDouble to-lat)}))))
)

(defn -main [& {:as args}]
  (web/run
    (->
      (web-middleware/wrap-session {:timeout 20})
      (web-middleware/wrap-websocket websocket-callbacks))
    (merge {"host" "0.0.0.0", "port" 8002, "path" "/geolocation/async"} args))
  (web/run
    (-> routes
      (web-middleware/wrap-session {:timeout 20}))
    (merge {"host" "0.0.0.0", "port" 8001, "path" "/geolocation"} args))
)