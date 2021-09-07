(ns tic-tac-tojure.main
  (:require [reagent.core :as r]
            [reagent.dom :as dom]))

(defn sym
  "print player symbol 0 = space, 1 = x, 2 = O"
  [n]
  (case n
    0 ""
    1 "X"
    2 "O"))

(defn win-state?
  [[a b c]]
  (and (not-any? #(= 0 %) [a b c])
       (every? #(= a %) [a b c])
       (and a b c)))

(defn check-row
  [board row]
  (let [check (board row)]
    (win-state? check)))

(defn check-all-rows
  [board]
  (or (check-row board 0) (check-row board 1) (check-row board 2)))

(defn check-col
  [board col]
  (let [check (map #(% col) board)]
    (win-state? check)))

(defn check-all-cols
  [board]
  (or (check-col board 0) (check-col board 1) (check-col board 2)))

(defn check-left-diag
  [board]
  (let [check (map #((board %) %) [0 1 2])]
    (win-state? check)))

(defn check-right-diag
  [board]
  (let [check [((board 0) 2) ((board 1) 1) ((board 2) 0)]]
    (win-state? check)))

(defn check-all
  [board]
  (or (check-all-rows board)
      (check-all-cols board)
      (check-left-diag board)
      (check-right-diag board)))

(defn occupied?
  [board row col]
  (not= 0 ((board row) col)))

(def board (r/atom [[0 0 0]
                    [0 0 0]
                    [0 0 0]]))

(def turn (atom 1))

(defn check-for-tie
  []
  (when  (->>  (flatten @board)
               (map #(or  (= % 1)  (= % 2)))
               (every? true?))
    (js/alert  "Tie game!")
    (reset! turn 1)
    (reset! board  [[0 0 0]  [0 0 0]  [0 0 0]])))

(defn game-over
  []
  (js/alert (str (sym @turn) " wins!"))
  (reset! turn 1)
  (reset! board [[0 0 0] [0 0 0] [0 0 0]]))

(defn validate-input
  [i]
  (let [parsed (js/parseInt i)
        valid? (and (>= i 0)
                    (< i 3)
                    (= (type parsed) ;; this is heinous I need to figure out how to check for NaN
                       (type 0)))]
    (if valid?
      parsed
      (js/alert (str i " is an invalid input"))))) ; handle NaN in this case more gracefully

(defn check-occupied
  [board row col]
  (if (not= 0 ((board row) col))
    (js/alert (str "Space: " row ", " col " is occupied"))
    true))

(defn set-2d
  [arr row col val]
  (assoc arr row
         (assoc (arr row) col val)))

(defn next-turn
  []
  (if (= @turn 1)
    (reset! turn 2)
    (reset! turn 1)))

(defn box-click
  [row col]
  (when (and (validate-input row)
             (validate-input col)
             (check-occupied @board row col))
    (swap! board set-2d row col @turn)
    (if (check-all @board)
      (game-over)
      (do (next-turn)
          (check-for-tie))))) ; check after so next-turn doesn't mess with the fresh game

(defn show-board
  []
  (let  [ind  (mapv sym  (flatten @board))]
    [:table
     [:tr
      [:td            {:on-click #(box-click 0 0)}  (ind 0)]
      [:td.vertical   {:on-click #(box-click 0 1)}  (ind 1)]
      [:td            {:on-click #(box-click 0 2)}  (ind 2)]]
     [:tr
      [:td.horizontal  {:on-click #(box-click 1 0)}  (ind 3)]
      [:td.center     {:on-click #(box-click 1 1)}  (ind 4)]
      [:td.horizontal  {:on-click #(box-click 1 2)}  (ind 5)]]
     [:tr
      [:td            {:on-click #(box-click 2 0)}  (ind 6)]
      [:td.vertical   {:on-click #(box-click 2 1)}  (ind 7)]
      [:td            {:on-click #(box-click 2 2)}  (ind 8)]]]))

(defn show-page
  []
  (fn  []
    [:div
     [:h3  "It's "  (sym @turn)  "'s turn"]
     (show-board)]))

(dom/render  [show-page]
             (.-body js/document))

