(ns core  
  (:require [compojure.handler :as handler]
            [ring.adapter.jetty :as jetty]
            [ring.middleware.json :as middleware]
            [routes :as r]))

(def app
  (-> (handler/api r/routes)
      (middleware/wrap-json-body {:keywords? true})
      (middleware/wrap-json-params)
      (middleware/wrap-json-response)))

(defonce server (jetty/run-jetty #'app {:port 8080 :join? false}))

(defn -main [& _args]
  (println "Hello, World!")
  (.start server))

(comment
  (.stop server))
