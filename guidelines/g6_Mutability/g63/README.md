# MUTABLE-3: Create safe copies of mutable and subclassable input values
Mutable objects may be changed after and even during the execution of a method or constructor call. Types that can be subclassed may behave incorrectly, inconsistently, and/or maliciously. If a method is not specified to operate directly on a mutable input parameter, create a copy of that input and perform the method logic on the copy. In fact, if the input is stored in a field, the caller can exploit race conditions in the enclosing class. For example, a time-of-check, time-of-use inconsistency (TOCTOU) [7] can be exploited where a mutable input contains one value during a SecurityManager check but a different value when the input is used later.

To create a copy of an untrusted mutable object, call a copy constructor or creation method:

        public final class CopyMutableInput {
            private final Date date;

            // java.util.Date is mutable
            public CopyMutableInput(Date date) {
                // create copy
                this.date = new Date(date.getTime());
            }
        }

In rare cases it may be safe to call a copy method on the instance itself. For instance, java.net.HttpCookie is mutable but final and provides a public clone method for acquiring copies of its instances.

        public final class CopyCookie {

            // java.net.HttpCookie is mutable
            public void copyMutableInput(HttpCookie cookie) {
                // create copy
                cookie = (HttpCookie)cookie.clone(); // HttpCookie is final

                // perform logic (including relevant security checks)
                // on copy
                doLogic(cookie);
            }
        }

It is safe to call HttpCookie.clone because it cannot be overridden with a malicious implementation. Date also provides a public clone method, but because the method is overrideable it can be trusted only if the Date object is from a trusted source. Some classes, such as java.io.File, are subclassable even though they appear to be immutable.

This guideline does not apply to classes that are designed to wrap a target object. For instance, java.util.Arrays.asList operates directly on the supplied array without copying.

In some cases, notably collections, a method may require a deeper copy of an input object than the one returned via that input's copy constructor or clone method. Instantiating an ArrayList with a collection, for example, produces a shallow copy of the original collection instance. Both the copy and the original share references to the same elements. If the elements are mutable, then a deep copy over the elements is required:

         // String is immutable.
         public void shallowCopy(Collection<String> strs) {
             strs = new ArrayList<String>(strs);
             doLogic(strs);
         }
         // Date is mutable.
         public void deepCopy(Collection<Date> dates) {
             Collection<Date> datesCopy = new ArrayList<Date>(dates.size());
             for (Date date : dates) {
                 datesCopy.add(new java.util.Date(date.getTime()));
             }
             doLogic(datesCopy);
         }

Constructors should complete the deep copy before assigning values to a field. An object should never be in a state where it references untrusted data, even briefly. Further, objects assigned to fields should never have referenced untrusted data due to the dangers of unsafe publication.


## Others
 - MUTABLE-1: [Prefer immutability for value types](../g61)
 - MUTABLE-2: [Create copies of mutable output values](../g62)
 - MUTABLE-3: Create safe copies of mutable and subclassable input values
 - MUTABLE-4: [Support copy functionality for a mutable class](../g64)
 - MUTABLE-5: [Do not trust identity equality when overridable on input reference objects](../g65)
 - MUTABLE-6: [Treat passing input to untrusted object as output](../g66)
 - MUTABLE-7: [Treat output from untrusted object as input](../g67)
 - MUTABLE-8: [Define wrapper methods around modifiable](../g68)
 - MUTABLE-9: [Make public static fields final](../g69)
 - MUTABLE-10: [Ensure public static final field values are constants](../g610)
 - MUTABLE-11: [Do not expose mutable statics](../g611)
 - MUTABLE-12: [Do not expose modifiable collections](../g612)
