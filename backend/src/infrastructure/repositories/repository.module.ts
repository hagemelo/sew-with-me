import { Global, Module } from "@nestjs/common";
import { OrderImplRepository } from "./order-impl.repository";
import { OrderRepository } from "@domain/order.repository";

@Global()
@Module({
    imports: [],
    providers: [{
        provide: OrderRepository,
        useClass: OrderImplRepository
      }],
    exports: [OrderRepository],
})
export class RepositoryModule {}