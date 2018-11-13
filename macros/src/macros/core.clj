(ns macros.core)

(defmacro when-macro [condition & forms]
  `(if ~condition (do ~@forms)))

(defmacro when-one [condition & forms]
  (list 'if condition (cons 'do forms)))

(defmacro thread-last
  ([form] form)
  ([form & forms] (if forms
                    (let [f (first forms)]
                      `(~@f ~form))
                   form)))
