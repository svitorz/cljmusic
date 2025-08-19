(ns routes
  (:require [compojure.core :refer [GET defroutes]]
            [compojure.route :as route]))

(defroutes routes 
  (GET "/musics" [] "<h1>CljMusic<h1>")
  (route/not-found "<h1>page not found<h1>"))

