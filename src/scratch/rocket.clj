(ns scratch.rocket)

(defn atlas-v
  []
  {:dry-mass  50050
   :fuel-mass 284450
   :time 0
   :isp 3050
   :max-fuel-rate (/ 284450 253)
   :max-thrust 4.152e6})

(defn mass
  "The total mass of a craft."
  [craft]
  (+ (:dry-mass craft) (:fuel-mass craft)))