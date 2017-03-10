(defproject br.com.reactive-poc/rest-service "1.0.0-SNAPSHOT"
  :description "Projeto que define os microservices reativos para a POC"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.6.0"]
                 [org.clojure/tools.logging "0.3.1"]
                 [org.immutant/web "2.1.6"]
                 [compojure "1.1.8"]
                 [ring/ring-core "1.3.0"]
                 [environ "1.0.0"]]
  :main br.com.reactive-poc.rest-service
)
