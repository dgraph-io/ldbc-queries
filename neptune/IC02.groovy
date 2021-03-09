g.V()
    .has('person', 'xid', %s) // personId
    .both('knows')
    .as('person')
    .in('hasCreator')
    .has('creationDate', lte(datetime('%s'))) // maxDate
    .order()
      .by('creationDate', desc)
      .by('xid', asc)
      .limit(%d) // limit
      .as('message')
    .select('person', 'message')
      .by(valueMap('xid', 'firstName', 'lastName'))
      .by(valueMap('xid', 'imageFile', 'content', 'creationDate'))