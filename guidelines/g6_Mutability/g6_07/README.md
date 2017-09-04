# MUTABLE-7: Treat output from untrusted object as input
![Author](https://img.shields.io/badge/Author-Oracle-blue.svg)


The above guidelines on input objects apply when returned from untrusted objects. Appropriate copying and validation should be applied.

        private final Date start;
        private Date end;

        public void endWith(Event event) throws IOException {
            Date end = new Date(event.getDate().getTime());
            if (end.before(start)) {
                throw new IllegalArgumentException("...");
            }
            this.end = end;
        }

