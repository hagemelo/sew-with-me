
import { Order } from "@domain/models/order.model";
import { OrderDomain } from "@domain/order.domain";
import { faker } from "@faker-js/faker";
import { makeClientFake } from "./client.fake";
import { makeSewFake } from "./sew.fake";
import { OrderStatus } from "@domain/models/order-status.model";

export const makeOrderFake = (props?: Partial<Order>): OrderDomain => new OrderDomain({
    id: props.id || faker.number.int({ max: 100000 }),
    client:  props.client || makeClientFake(),
    created_at: props.created_at || new Date(),
	value: props.value || faker.number.int({ max: 100000 }),
    status: props.status || OrderStatus.ABERTO,
    deliveryForecast: props.deliveryForecast || new Date(),
    sews: props.sews || [makeSewFake()]
  })