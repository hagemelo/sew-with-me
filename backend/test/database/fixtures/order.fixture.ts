import { type DataSource } from 'typeorm'
import { Fixture } from '@infrastructure/database/data-source/fixture'
import { Order } from '@domain/models/order.model'
import { makeOrderFake } from 'test/fake/order.fake'


export class OrderFixture extends Fixture<Order> {
  static create (dataSource: DataSource): Fixture<Order> {
    return new OrderFixture({
      createDefault: makeOrderFake,
      repositoryName: 'Order',
      dataSource
    })
  }
}