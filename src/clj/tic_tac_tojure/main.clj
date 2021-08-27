(ns tic-tac-tojure.main
  (:require
    [compojure.core :refer [defroutes GET]]
    [compojure.route :as route]
    [ring.util.response]
    [ring.adapter.jetty :refer [run-jetty]]))
  
(defroutes routes
           (GET "/" [] (ring.util.response/redirect "/index.html")) 
           (route/files "/" {:root "./resources/"})
           (route/files "/resources/css" {:root "./resources/css"})
           (route/files "/resources/js" {:root "./resources/js"})
           (route/not-found "Not found"))

(defn -main
  []
  (run-jetty
    routes
    {:port  8080
     :join? false}))
