(ns tic-tac-tojure.main-test
  (:require
   [cljs.test :refer-macros [deftest is testing]]
   [tic-tac-tojure.main :refer [check-row]]))

(deftest check-row-test
  (testing "check-row"
    (let [b [[0 1 0]
             [1 1 1]
             [0 0 0]]]
      (testing "1 mark in row returns false"
        (is (= false
               (check-row b 0))))
      (testing "3 1s in a row returns 1"
        (is (= 1
               (check-row b 1))))
      (testing "3 0s in a row returns false"
        (is (= false
               (check-row b 2)))))
    (let [b [[2 2 2]
             [2 0 2]
             [2 2 0]]]
      (testing "3 2s in a row returns 2"
        (is (= 2
               (check-row b 0))))
      (testing "2 2s in a row returns false"
        (is (= false
               (check-row b 2)))))))
