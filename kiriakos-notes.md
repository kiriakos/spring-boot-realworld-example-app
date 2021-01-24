# Kiriakos Notes 2021-01-24

- Let's try lobmok!
- Let's try gradle!
- MyBatis vs Orika
- Postman!! :-D

## Other Ideas

### Make MyBatis `@Param` resolve the fieldname?

```
(@Param("articleFavorite") ArticleFavorite articleFavorite);
```

### Change Article.update() and User.update()?

In order not to generate setters the Authors have decided to force all 
object mutations of Article and User beans though the update method.

The sideefect of this is that to change one field one has to invoke a
method with n arguments (where n the number of mutbale fields).

> _Where does the .data. package fit in?_

### RealworldApplication.java, no Componentscan directives

Does the app really scan the whole classpath???


### Really little documentation on service and infrastructure classes

You probably don't need to javadoc every method you write but general
documentation about the purpose of a Utility or Infrastructure class is
helpful, especially to future maintainers.

### Applying DS Style breaks `CurrentUserApiTest`???

Aparently applying the DS style and cleaning up the project causes 
`should_get_error_if_email_exists_when_update_user_profile` test to fail

### Why use string for identity instead of a uuid type?

the Persistence beans 

### Responses with root elements

We could do away with the `M<K,V>` response types riddled around and the `?` in the Api 
classes by creating a _response factory_ 

#### Eliminate `ResponseEntity.ok` instances for better types

Spring Web Handlers wrap a custom return type with a `ResponseEntity.ok` per default
in order to make the xxxApi classes a bit more readable we can eliminate return sigs
of `ResponseEntity` `ResponseEntity<?>` and `ResponseEntity<Map>` with more descriptive
types as long as they return `ResponseEntity.ok`.
