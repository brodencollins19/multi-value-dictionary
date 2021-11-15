package dictionary

import Command.DictionaryCommandException

class MultiValueDictionary {
    Map<String, Set<String>> values


    /*
        Returns all keys from the dictionary
     */

    Set<String> getKeys() {
        return values.keySet()
    }

    /*
        Returns all members for a given key
     */

    Set<String> getMembers(String key) {
        validateKeyExists(key)
        return values.get(key)
    }


    /*
        Adds a new key value pair to the dictionary with the given key and member
     */

    void addItem(String key, String member) {
        validateMemberForKeyAlreadyExists(key, member)

        if (!values.keySet().contains(key)) {

            values.put(key, new HashSet<String>())
        }
        values.get(key).add(member)

    }

    /*
        Removes the key value pair for the given key and member from the dictionary, if the last member is removed
        removes the key from the dictioanry
     */

    void removeItem(String key, String member) {
        validateKeyExists(key)

        validateMemberForKeyDoesNotExist(key, member)

        values.get(key).remove(member)

        if (values.get(key).isEmpty()) {
            values.remove(key)
        }
    }


    /*
        Removes all items for the given key in the dictionary
     */

    void removeAll(String key) {
        validateKeyExists(key)
        values.get(key).removeAll()
        values.remove(key)
    }


    /*
        Clears the dictionary
     */

    void clear() {
        values.clear()
    }

    /*
        Return true if the given key exists, false otherwise
     */

    boolean keyExists(String key) {
        if (values.keySet().contains(key)) {
            return true
        }
        return false
    }

    /*
        Return true if the given member exists for the give key, false otherwise
     */

    boolean memberExists(String key, member) {
        if (!keyExists(key)) {
            return false
        }

        if (values.get(key).contains(member)) {
            return true
        }

        return false

    }

    /*
        Get all members in the dictionary
     */

    List<String> getAllMembers() {
        List<String> allMembers = []

        values.keySet().each { key ->
            values.get(key).each { memberForKey ->
                allMembers.add(memberForKey)
            }
        }

        return allMembers

    }

    /*
        Get all items from the dictionary as a key value pair
     */

    List<Item> getAllItems() {
        List<Item> allItems = []

        values.keySet().each { key ->
            values.get(key).each { memberForKey ->
                allItems.add(new Item(key: key, value: memberForKey))
            }
        }
        return allItems

    }


    /*
       Validates if a key exists in the dictionary, throws an exception if it is missing
    */

    private void validateKeyExists(String key) {
        if (!values.keySet().contains(key)) {
            throw new DictionaryCommandException("key does not exist")
        }

    }

    /*
        Validates if a member for a given key already exists in the dictionary (ie. when adding a new item),
        throws an exception if it is already present
    */

    private void validateMemberForKeyAlreadyExists(String key, String member) {
        Set<String> existingItemsForKey = values.get(key)

        if (existingItemsForKey && existingItemsForKey?.contains(member)) {
            throw new DictionaryCommandException("member already exists for key")
        }
    }

    /*
    Validates if a member for a given key already exists in the dictionary, throws an exception if it is missing
    */

    private void validateMemberForKeyDoesNotExist(String key, String member) {
        Set<String> existingItemsForKey = values.get(key)

        if (!existingItemsForKey || !existingItemsForKey.contains(member)) {
            throw new DictionaryCommandException("member does not exist")
        }
    }


}
