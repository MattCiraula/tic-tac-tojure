(ns tic-tac-tojure.view
  (:require [hiccup.page :refer [html5]]))

(def index
  (html5
   [:head
    [:meta {:charset "UTF-8"}]
    [:link {:rel "stylesheet" :href "resources/css/styles.css"}]]
   [:body
    [:script {:src "resources/js/main.js" :type "text/javascript"}]]))
