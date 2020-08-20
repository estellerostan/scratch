(ns scratch.core
  (:require
   [clojure.pprint :as p]))

(defn pow
  "Raises base to the given power. For instance, (pow 3 2) returns three squared, or nine."
  [base power]
  (apply * (repeat power base)))
