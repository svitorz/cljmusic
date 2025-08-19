(ns core  
  (:require [compojure.handler :as handler]
            [ring.adapter.jetty :as jetty]
            [ring.middleware.json :as middleware]
            [routes :as r]
            [database :as db]))

(def app
  (-> (handler/api r/routes)
      (middleware/wrap-json-body {:keywords? true})
      (middleware/wrap-json-params)
      (middleware/wrap-json-response)))

(defonce server (jetty/run-jetty #'app {:port 8080 :join? false}))


(defn -main [& _args]
  (println db/db-name)
  (.start server))

(comment
  (.stop server))
