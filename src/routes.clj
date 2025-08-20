(ns routes
  (:require [compojure.core :refer [GET defroutes]]
            [compojure.route :as route]
            [datomic.api :as d]
            [database :as db]
            [cheshire.core :as json]))

(def db-uri (System/getenv "DB_URI"))

(defn all-songs []
  (let [conn (d/connect db-uri)
        db   (d/db conn)]
    (d/q db/all-songs-q db)))

(defroutes routes 
  (GET "/musics" []
    {:status 200
     :headers {"Content-Type" "application/json"}
     :body (json/generate-string (all-songs))})
  (route/not-found {:status 404 
     :headers {"Content-Type" "application/json"}
     :body (json/generate-string "Page not found")}))

