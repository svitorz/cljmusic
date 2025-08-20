(ns database
  [:require [datomic.api :as d]])

(def db-uri (System/getenv "DB_URI"))

(d/create-database db-uri)

(def conn (d/connect db-uri))

@(d/transact conn [{:db/doc "Hello, World!"}])

(def music-schema [{:db/ident :music/title
                    :db/valueType :db.type/string
                    :db/cardinality :db.cardinality/one 
                    :db/doc "The name of the music"}
                   
                    {:db/ident :music/artist_name
                    :db/valueType :db.type/string
                    :db/cardinality :db.cardinality/one 
                    :db/doc "The name of the artist who created the music"}

                    {:db/ident :music/release_date
                    :db/valueType :db.type/long
                    :db/cardinality :db.cardinality/one 
                    :db/doc "The date of release"}])

@(d/transact conn music-schema)

(def first-songs [{:music/title "November Rain"
                   :music/artist_name "Guns N' Roses" 
                   :music/release_date 1991 }
                  {:music/title "Na Maldade"
                   :music/artist_name "Simone Morena" 
                   :music/release_date 2023 }
                  {:music/title "Amina"
                   :music/artist_name "Tasha & Tracie" 
                   :music/release_date 2025 }])

@(d/transact conn first-songs)

(def all-songs-q '[:find ?e ?title
                   :where[?e :music/title ?title]])
