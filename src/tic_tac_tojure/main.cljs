(ns tic-tac-tojure.main)

(defn sym
  "print player symbol 0 = space, 1 = x, 2 = O"
  [n]
  (case n
    0 " "
    1 "X"
    2 "O"))

(defn print-board
  [b]
  (println (str " " (sym ((b 0) 0)) " | " (sym ((b 0) 1)) " | " (sym ((b 0) 2))))
  (println "___|___|___")
  (println (str " " (sym ((b 1) 0)) " | " (sym ((b 1) 1)) " | " (sym ((b 1) 2))))
  (println "___|___|___")
  (println (str " " (sym ((b 2) 0)) " | " (sym ((b 2) 1)) " | " (sym ((b 2) 2))))
  (println "   |   |   "))

(defn win-state?
  [[a b c]]
  (and (not-any? #(= 0 %) [a b c])
       (every? #(= a %) [a b c])
       (and a b c)))

(defn check-row
  [board row]
  (let [check (board row)]
    (win-state? check)))

(defn check-col
  [board col]
  (let [check (map #(% col) board)]
    (win-state? check)))

(defn check-left-diag
  [board]
  (let [check (map #((board %) %) [0 1 2])]
    (win-state? check)))

(defn check-right-diag
  [board]
  (let [check [((board 0) 2) ((board 1) 1) ((board 2) 0)]]
    (win-state? check)))

(defn game-loop
  [player board]
  (print-board board))

(defn -main
  [& _args]
  (game-loop 1
             [[0 0 0]
              [0 0 0]
              [0 0 0]]))
