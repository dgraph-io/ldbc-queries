g.V()
    .has('person', 'xid', %s) // person1Id
    .choose(repeat(both('knows').dedup())
    .until(has('person', 'xid', '%s')) // person2Id
    .limit(1)
    .path()
    .count(local)
    .is(gt(0)), repeat(store('x')
    .both('knows')
    .where(without('x'))
    .aggregate('x'))
    .until(has('person', 'xid', '%s')) // person2Id
    .limit(1)
    .path()
    .count(local), constant(-1)).fold()