g.withSack(0).V()
    .has('person', 'xid', %s) // personId
    .repeat(both('knows')
    .simplePath()
    .sack(sum)
      .by(constant(1)))
    .emit()
    .times(3)
    .dedup()
    .has('person', 'firstName', '%s') // firstName
    .order()
      .by('lastName', asc)
      .by('xid', asc)
    .limit(%d) // limit
    .local(union(
      valueMap('lastName', 'xid', 'email', 'birthday', 'creationDate', 'gender', 'browserUsed', 'locationIP', 'language'),
      out('isLocatedIn').valueMap('name'),
      sack().fold(),
      outE('workAt')
        .as('workFrom')
        .inV()
          .as('company')
        .out('isLocatedIn')
          .as('country')
        .select('company', 'workFrom', 'country')
          .by(valueMap('name'))
          .by(valueMap())
          .fold(),
      outE('studyAt')
        .as('studyFrom')
        .inV()
          .as('university')
        .out('isLocatedIn')
          .as('country')
        .select('university', 'studyFrom', 'country')
          .by(valueMap('name'))
          .by(valueMap())
          .fold()).fold())