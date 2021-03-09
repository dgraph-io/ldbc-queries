g.V()
    .has('person', 'xid', %s) // personId
    .in('hasCreator')
    .in('replyOf')
    .as('message')
    .order()
      .by('creationDate', desc)
      .by('xid', asc)
    .limit(%d) // limit
    .out('hasCreator')
    .as('person')
    .select('message', 'person')
      .by(valueMap('xid', 'creationDate', 'content'))
      .by(valueMap('xid', 'firstName', 'lastName'))