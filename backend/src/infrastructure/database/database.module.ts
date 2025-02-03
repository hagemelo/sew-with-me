import * as path from 'path' 
import { Global, Module } from '@nestjs/common'
import { ConfigModule, ConfigService } from '@nestjs/config'
import { TypeOrmModule } from '@nestjs/typeorm'
import { ClientEntity } from '@infrastructure/entities/client.entity'
import { OrderEntity } from '@infrastructure/entities/order.entity'
import { SewEntity } from '@infrastructure/entities/sew.entity'
import { MaterialUsedEntity } from '@infrastructure/entities/material-used.entity'
import { MaterialEntity } from '@infrastructure/entities/material.entity'
import dbConfig from './config'
import { addTransactionalDataSource } from 'typeorm-transactional'
import { DataSource } from 'typeorm'

@Global()
@Module({
    imports: [
        TypeOrmModule.forRootAsync({
          useFactory() {
            return dbConfig.options;
          },
          async dataSourceFactory(options) {
            if (!options) {
              throw new Error('Invalid options passed');
            }
    
            return addTransactionalDataSource(new DataSource(options));
          },
        }),
        TypeOrmModule.forFeature([ClientEntity, OrderEntity, SewEntity, MaterialUsedEntity, MaterialEntity]),
      ],
    exports: [TypeOrmModule],
})
export class DatabaseModule {}