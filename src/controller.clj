(ns controller
  (:require [datomic.api :as d] 
            [database :as db]
            [ring.util.response :as resp]))


(defn all-songs []
  (let [conn (d/connect db/db-uri)
        db   (d/db conn)
        rows (d/q db/all-songs-q db) ]
        (-> 
        (resp/response (vec rows))
        (resp/content-type "application/json"))))

(defn create-song [request]
  (let [conn (d/connect db/db-uri)
        title (get-in request [:params "title"])
        artist_name (get-in request [:params "artist_name"])
        release_date (get-in request [:params "release_date"])
        row (d/transact conn [{:music/title title
                            :music/artist_name artist_name
                            :music/release_date release_date}])])
  (->
    (resp/response "Created.") 
    (resp/content-type "application/json")))
