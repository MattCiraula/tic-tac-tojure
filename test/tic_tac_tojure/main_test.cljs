(ns tic-tac-tojure.main-test
  (:require
   [cljs.test :refer-macros [deftest is testing]]
   [tic-tac-tojure.main :refer [check-row
                                check-col
                                check-left-diag
                                check-right-diag]]))

(deftest check-row-test
  (testing "check-row:"
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

(deftest check-col-test
  (testing "check-col:"
    (let [b [[1 0 0]
             [1 0 1]
             [1 0 0]]]
      (testing "1 mark in col returns false"
        (is (= false
               (check-col b 2))))
      (testing "3 1s in a row returns 1"
        (is (= 1
               (check-col b 0))))
      (testing "3 0s in a col returns false"
        (is (= false
               (check-col b 1)))))
    (let [b [[2 0 2]
             [2 0 0]
             [2 0 2]]]
      (testing "3 2s in a col returns 2"
        (is (= 2
               (check-col b 0))))
      (testing "2 2s in a col returns false"
        (is (= false
               (check-col b 2)))))))

(deftest check-left-diag-test
  (testing "check-left-diag:"
    (testing "3 0s in left diag returns false"
      (is (= false
             (check-left-diag [[0 1 1]
                               [1 0 1]
                               [1 1 0]]))))
    (testing "3 1s in left diag returns 1"
      (is (= 1
             (check-left-diag [[1 0 0]
                               [0 1 0]
                               [0 0 1]]))))
    (testing "3 2s in left diag returns 2"
      (is (= 2
             (check-left-diag [[2 0 0]
                               [0 2 0]
                               [0 0 2]]))))
    (testing "2 1s in left diag returns false"
      (is (= false
             (check-left-diag [[1 1 1]
                               [1 1 1]
                               [1 1 0]]))))
    (testing "1 2 in left diag returns false"
      (is (= false
             (check-left-diag [[2 2 2]
                               [2 0 2]
                               [2 2 0]]))))))

(deftest check-right-diag-test
  (testing "check-right-diag:"
    (testing "3 0s in right diag returns false"
      (is (= false
             (check-right-diag [[1 1 0]
                                [1 0 1]
                                [0 1 1]]))))
    (testing "3 1s in right diag returns 1"
      (is (= 1
             (check-right-diag [[0 0 1]
                                [0 1 0]
                                [1 0 0]]))))
    (testing "3 2s in right diag returns 2"
      (is (= 2
             (check-right-diag [[1 1 2]
                                [1 2 1]
                                [2 1 1]]))))
    (testing "2 1s in right diag returns false"
      (is (= false
             (check-right-diag [[1 1 1]
                                [1 1 1]
                                [0 1 1]]))))
    (testing "1 2 in the right diag returns false"
      (is (= false
             (check-right-diag [[0 0 1]
                                [0 2 0]
                                [0 0 0]]))))))
