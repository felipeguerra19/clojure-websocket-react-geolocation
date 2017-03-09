(ns br.com.reactive-poc.rest-service-test
  (:require [clojure.test :refer :all]
            [br.com.reactive-poc.rest-service :refer :all]
            [br.com.reactive-poc.distance :refer :all]))


(deftest distance-test-equal-0
  (testing "Distance between two identical points should be 0"
    (let [result (distance {:source/lng -46.692071, :source/lat -23.529398} {:to/lng -46.692071, :to/lat -23.529398})]
      (is (= result 0.0)))))


(deftest distance-test
  (testing "Distance between two diferent points should be > 0"
    (let [result (distance {:source/lng -46.6248598, :source/lat -23.4994384} {:to/lng -46.6352024, :to/lat -23.5095329})]
      (print result)
      (is (> result 0.0)))))