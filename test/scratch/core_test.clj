(ns scratch.core-test
  (:require [clojure.test :refer :all]
            [scratch.core :refer :all]))

(deftest pow-test
  (testing "unity"
    (is (= 1 (pow 1 1))))

  (testing "square integers"
    (is (= 9 (pow 3 2))))

  (testing "0^0"
    (is (= 1 (pow 0 0)))))