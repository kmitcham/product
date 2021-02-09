[![Build status](https://ci.appveyor.com/api/projects/status/nin1kjvthfvq6u0t?svg=true)](https://ci.appveyor.com/project/kmitcham/product)

# product
Update product.inputfile in application.properties to point to desired input file.
In hindsight, Spring Boot seems a bit overkill for this service; I chose it as a framework to help deal with the infrastructure (automated builds and the like.)
I don't have a lot of familiarity with green-fielding from scratch, so all of that is rougher than I would like.
I could polish this and improve some things, but as in real production, delay isn't free.
I am still not super happy with how I am handling the field parsing; it works well enough for this small example, but I have concerns 
about it generating code smell scaling up 100x.

Start the application with 
./gradlew bootRun

