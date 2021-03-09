g.V()
    .has('person', 'xid', %s) // personId
    .repeat(both('knows').simplePath())
    .emit()
    .times(2)
    .dedup()
    .in('hasCreator')
    .hasLabel('post')
    .where(out('hasTag').has('tag', 'name', '%s')) // tagName
    .out('hasTag')
    .has('tag', 'name', neq('%s')) // tagName
    .groupCount()
      .by('name')
    .order(local)
      .by(values, desc)
    .unfold()
    .limit(%d) // limit
    .fold()