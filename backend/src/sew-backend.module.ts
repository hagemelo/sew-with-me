import { Module } from '@nestjs/common';
import { TypeOrmModule } from '@nestjs/typeorm';
import { ConfigModule, ConfigService } from '@nestjs/config';
import { DatabaseModule } from './infrastructure/database/database.module';
import { ListOrderQuery } from './interface/api/list-order-query';
import { ListOrderQueryUseCase } from './application/use-cases/list-order-query.use-case';
import { OrderRepository } from './domain/order.repository';
import { Order } from './domain/models/order.model';
import { RepositoryModule } from '@infrastructure/repositories/repository.module';

const useCases = [ListOrderQueryUseCase];

@Module({
  imports: [DatabaseModule, RepositoryModule],
  controllers: [ListOrderQuery],
  providers: [...useCases],
})
export class SewBackendModule {}
