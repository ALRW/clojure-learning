(ns macros.core)

(defmacro when-macro [condition & forms]
  `(if ~condition (do ~@forms)))

(defmacro when-one [condition & forms]
  (list 'if condition (cons 'do forms)))

(defn convert-forms
  [form forms]
  (let [next-form (first forms)]
    (if next-form
      (let [threaded (if (seq? next-form)
                       `(~@next-form ~form)
                       (list next-form form))]
        (recur threaded (next forms)))
      form)))

(defmacro thread-last
  ([form & forms]
   (convert-forms form forms)))

(defn convert-forms* 
  [form forms]
  (let [next-form (first forms)]
    (if next-form
      (let [threaded (if (seq? next-form)
                       `(~(first next-form)
                                 ~form
                                 ~@(next next-form))
                       (list next-form form))]
        (recur threaded (next forms)))
      form)))

(defmacro thread-first
  [form & forms]
  (convert-forms* form forms))
