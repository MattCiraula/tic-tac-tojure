(ns tic-tac-tojure.main
  (:require
   [compojure.core :refer [defroutes GET]]
   [compojure.route :as route]
   [ring.util.response]
   [tic-tac-tojure.view :as view]
   [ring.adapter.jetty :refer [run-jetty]]))

(defroutes routes
  (GET "/" [] {:status 200
               :headers {"Content-Type" "text/html"}
               :body view/index})
  (route/files "/resources/css" {:root "./resources/css"})
  (route/files "/resources/js" {:root "./resources/js"})
  (route/not-found "Not found"))

(defn -main
  []
  (run-jetty
   routes
   {:port  8080
    :join? false}))
