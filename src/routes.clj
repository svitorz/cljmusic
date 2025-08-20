(ns routes
  (:require [compojure.core :refer [GET defroutes]]
            [controller :as ctr]
            [ring.util.response :as resp]
            [compojure.route :as route]))



(defroutes routes 
  (GET "/musics" [] (ctr/all-songs))
  (route/not-found (resp/not-found {:body "Page not found"}))
  )

