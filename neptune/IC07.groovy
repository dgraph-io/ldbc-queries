g.V()
    .has('person', 'xid', %s) // personId
    .in('hasCreator')
        .as('message')
    .inE('likes')
        .as('like')
    .outV()
        .as('person')
    .dedup()
    .order()
        .by(select('person').by('xid'), desc)
    .limit(%d) // limit
    .choose(
        both('knows').has('person', 'xid', %s), // personId
        constant(false),
        constant(true))
        .as('isNew')
    .select('person', 'message', 'isNew', 'like')
        .by(valueMap('xid', 'firstName', 'lastName'))
        .by(valueMap('xid', 'content', 'imageFile', 'creationDate'))
        .by(fold())
        .by(valueMap('creationDate'))