Conversion of Observable.
| From / To | Flowable | Observable | Maybe |Single | Completeable|
| -------- | ------- |------- |------- |------- |------- |
| Flowable |  | toObservable()| elementAt() reduce() firstElement() lastElement() singleElement()| scan() elementAt() first() firstOrError() single() singleOrError() last() lastOrError() all() any() count()| ignoreElements()|
| Observable |  toFlowable()| |elementAt() reduce() firstElement() lastElement() singleElement() |  scan() elementAt() first() firstOrError() single() singleOrError() last() lastOrError() all() any()| ignoreElements()|
| Maybe |  toFlowable()| toObservable()| |toSingle() seruenceEqual()  | toCompleteable() |
| Single |  toFlowable()| toObservable()| toMaybe()|  | toCompleteable()|
| Completeable|  toFlowable()| toObservable()| toMaybe()|  toSingle() toSingleDefault()|  |
#   R x j a v a _ s e r v i c e h 2  
 