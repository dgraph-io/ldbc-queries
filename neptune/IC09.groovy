g.V()
    .has('person', 'xid', %s) // personId
    .repeat(both('knows').simplePath())
    .emit()
    .times(2)
    .hasLabel('person')
    .dedup()
    .as('person')
    .in('hasCreator')
    .has('creationDate', lt(datetime('%s'))) // maxDate
    .order()
      .by('creationDate', desc)
      .by('xid', asc)
    .limit(%d) // limit
    .as('message')
    .select('person', 'message')
      .by(valueMap('xid', 'firstName', 'lastName'))
      .by(valueMap('xid', 'creationDate', 'content', 'imageFile'))