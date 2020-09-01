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

(defn magnitude
  "What's the radius of a given set of cartesian coordinates?"
  [c]
  ; By the Pythagorean theorem...
  (Math/sqrt (+ (Math/pow (:x c) 2)
                (Math/pow (:y c) 2)
                (Math/pow (:z c) 2))))

(defn cartesian->spherical
  "Converts a map of Cartesian coordinates :x, :y, and :z to spherical coordinates :r, :theta, and :phi."
  [c]
  (let [r (magnitude c)]
    {:r r
     :theta (Math/acos (/ (:z c) r))
     :phi   (Math/atan (/ (:y c) (:x c)))}))

(defn spherical->cartesian
  "Converts spherical to Cartesian coordinates."
  [c]
  {:x (* (:r c) (Math/sin (:theta c)) (Math/cos (:phi c)))
   :y (* (:r c) (Math/sin (:theta c)) (Math/sin (:phi c)))
   :z (* (:r c) (Math/cos (:phi c)))})

(def g "Acceleration of gravity in meters/s^2" -9.8)

(defn gravity-force
  "The force vector, each component in Newtons, due to gravity."
  [craft]
  ; Since force is mass times acceleration...
  (let [total-force (* g (mass craft))]
    (-> craft
        ; Now we'll take the craft's position
        :position
        ; in spherical coordinates,
        cartesian->spherical
        ; replace the radius with the gravitational force...
        (assoc :r total-force)
        ; and transform back to Cartesian-land
        spherical->cartesian)))
