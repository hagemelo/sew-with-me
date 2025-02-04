import dbConfig from '@infrastructure/database/config'
import { createPostgresDataSourceTest } from '@infrastructure/database/data-source/postgres-test'
import { DatabaseModule } from '@infrastructure/database/database.module'
import { ConfigModule } from '@nestjs/config'
import { Test, type TestingModule } from '@nestjs/testing'
import { SewBackendModule } from 'src/sew-backend.module'
import { DataSource } from 'typeorm'
import { OrderFixture } from './database/fixtures/order.fixture'

let testingModule: TestingModule

export const buildTestingModule = async (): Promise<TestingModule> => {
    const dataSource = await createPostgresDataSourceTest()

    testingModule = await Test.createTestingModule({
        imports: [
            ConfigModule.forRoot({
              load: [],
              isGlobal: true
            }),
            DatabaseModule,
            SewBackendModule
          ],
        providers: [
            {
                provide: DataSource,
                useValue: dataSource
            },
            {
                provide: 'clearDatabase',
                useValue: async () => {
                  for await (const entity of dataSource.entityMetadatas) {
                    await dataSource.getRepository(entity.name).clear()
                  }
                }
            },
            {
                provide: OrderFixture,
                useFactory: () => {
                  return OrderFixture.create(dataSource)
                }
              }
        ]
    }).overrideProvider(DataSource)
    .useValue(dataSource)
    .compile()
    return testingModule
}