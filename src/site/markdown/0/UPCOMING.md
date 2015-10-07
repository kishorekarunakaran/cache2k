# cache2k version 0.22 release notes

## Possible breakages

  * Semantics of Cache.getAll() changed. Instead of returning always a map size equal to the requested count of keys,
    only keys with a non-null mapping are returned in the map.
  * Added generic types to the methods CacheBuilder.entryExpiryCalculator and CacheBuilder.exceptionExpiryCalculator

## New and Noteworthy

  TODO: check API, add since, add comment!
  * entry processor scheme like in JSR107: Cache.invokeAll,
  * Beginning of JSR107 support.
  * classloader support

## Fixes and Improvements

  * Fixes in the handling for entry expiry and refresh: There are rare casen when entries are not refreshed/updated when expired
  * Cache.iterator(): Proper exception on wrong usage of iterator pattern
  * Cache.iterator(): Fix semantics for direct call to next() without call to hasNext()
  * Fix usage counter for clock and clock pro implementation together with clear() operation
  * Fix possible race condition in cache manager when adding and closing caches and requesting an iteration of the existing caches
  * Handle exceptions within the expiry calculator more gracefully (still needs work, entry state is inconsistent if an exceptions happens here)
  * ExceptionPropagator for customising the propagation of cached exception
  * Cache.contains(), does no access recording if no storage is attached. Change because JSR107 specifies that a contains() does not count as get.
    However, cache2k has no dedicated counters just for get. Treating the contains as an access to an entry has pros and cons.
  * JMX statistics: initial getHitRate() value is 0.0
  * JMX statistics: initial getMillisPerFetch() value is 0.0


## API Changes and new methods

  * new: Cache.peekAndReplace()
  * new: Cache.peekAndRemove()
  * new: Cache.peekAndPut()
  * new: Cache.remove(key, oldValue)
  * new: Cache.replace(key, oldValue, newValue)
  * new: Cache.replace(key, newValue)
  * new: Cache.peekAll()
  * new: Cache.isClosed()
  * new: CacheManager.isClosed()
  * new: Cache.getCacheManager()
  * new: Cache.removeAll()
  * new: Cache.invoke() and Cache.invokeAll()
  * new: Cache.putAll()

  * deprecated: CacheManager.isDestroyed(), replaced by isClosed()

  * Stronger typing: Added generic types to the methods CacheBuilder.entryExpiryCalculator and CacheBuilder.exceptionExpiryCalculator