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

(def earth-equatorial-radius
  "Radius of the earth, in meters"
  6378137)

(def earth-day
  "Length of an earth day, in seconds."
  86400)

(def earth-equatorial-speed
  "How fast points on the equator move, relative to the center of the earth,
  in meters/sec."
  (/ (* 2 Math/PI earth-equatorial-radius)
     earth-day))

(def initial-space-center
  "The initial position and velocity of the launch facility"
  {:time     0
   :position {:x earth-equatorial-radius
              :y 0
              :z 0}
   :velocity {:x 0
              :y earth-equatorial-speed
              :z 0}})

(defn prepare
  "Prepares a craft for launch from an equatorial space center."
  [craft]
  (merge craft initial-space-center))