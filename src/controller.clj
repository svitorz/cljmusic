(ns controller
  (:require [datomic.api :as d] 
            [database :as db]
            [ring.util.response :as resp]))


(def db-uri (System/getenv "DB_URI"))

(defn all-songs []
  (let [conn (d/connect db-uri)
        db   (d/db conn)
        rows (d/q db/all-songs-q db) ]
        (-> 
        (resp/response (vec rows))
        (resp/content-type "application/json"))))
