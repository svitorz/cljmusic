(ns routes
  (:require [compojure.core :refer [GET POST defroutes]]
            [controller :as ctr]
            [ring.util.response :as resp]
            [compojure.route :as route]))



(defroutes routes 
  (GET "/musics" [] (ctr/all-songs))
  (POST "/musics" req (ctr/create-song req)) 
  (route/not-found (resp/not-found {:body "Page not found"}))
  )

