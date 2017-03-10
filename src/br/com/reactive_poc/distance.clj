(ns br.com.reactive-poc.distance
  (:require 
    [clojure.tools.logging :as log])
   (:gen-class))

(def earth-radius-km 6372.795477598)

(defn distance
    "Calcula distância entre duas coordenadas geográficas em metros"
    ([source to]
        {:pre [every? (every-pred map? #(contains? % :lat) #(contains? % :lng)) [source to]]}
        (log/info "Distance from " (:source/lat source) "," (:source/lng source) " to " (:to/lat to) "," (:to/lng to))
        (let [lat1 (Math/toRadians (:source/lat source))
              lng1 (Math/toRadians (:source/lng source))
              lat2 (Math/toRadians (:to/lat to))
              lng2 (Math/toRadians (:to/lng to))
              half-dlat (/ (- lat2 lat1) 2)
              half-dlng (/ (- lng2 lng1) 2)
              sin2 (fn [x] (Math/pow (Math/sin x) 2))
              a (+ (sin2 half-dlat) (* (Math/cos lat1) (Math/cos lat2) (sin2 half-dlng)))]
             (* 2000 earth-radius-km (Math/atan2 (Math/sqrt a) (Math/sqrt (- 1 a)))))))