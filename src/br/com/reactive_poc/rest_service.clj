(ns br.com.reactive-poc.rest-service
  (:require 
    [clojure.tools.logging :as log]
    [immutant.web             :as web]
    [immutant.web.async       :as async]
    [immutant.web.middleware  :as web-middleware]
    [compojure.route          :as route]
    [environ.core             :refer (env)]
    [compojure.core           :refer (ANY GET defroutes)]
    [ring.util.response       :refer (response redirect content-type)]
    [br.com.reactive-poc.distance :refer :all])
  (:gen-class))

(def websocket-callbacks
  "WebSocket callback functions"
  {:on-open   (fn [channel]
    (async/send! channel "Ready to reverse your messages!"))
  :on-close   (fn [channel {:keys [code reason]}]
    (println "close code:" code "reason:" reason))
  :on-message (fn [ch m]
    (log/info "Async Request:" m)            
    (async/send! ch (apply str (reverse m))))}
)

(defroutes routes
  (GET "/calculate-distance/:from-lat/:from-lng/:to-lat/:to-lng" 
       [from-lat from-lng to-lat to-lng]
       (response (str (distance {:source/lng (Double/parseDouble from-lng), :source/lat (Double/parseDouble from-lat)} 
                                {:to/lng (Double/parseDouble to-lng), :to/lat (Double/parseDouble to-lat)}))))
)

(defn -main [& {:as args}]
  (web/run
    (->
      (web-middleware/wrap-session {:timeout 20})
      (web-middleware/wrap-websocket websocket-callbacks))
    (merge {"host" "localhost", "port" 8002, "path" "/geolocation/async"} args))
  (web/run
    (-> routes
      (web-middleware/wrap-session {:timeout 20}))
    (merge {"host" "localhost", "port" 8001, "path" "/geolocation"} args))
)