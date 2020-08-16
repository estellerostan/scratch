(ns scratch.core)

(defn pow
  "Raises base to the given power. For instance, (pow 3 2) returns three squared, or nine."
  [base power]
  (apply * (repeat power base)))

(use 'cheshire.core)
(def data (parse-string (slurp "resources/ucr-normalized-2008.json") true))

(->> data (map :driving_under_influence))