import { DataType, type IMemoryDb, newDb } from 'pg-mem'
import dbConfig from '../config'
import { DataSource } from 'typeorm'

let db: IMemoryDb

const initDatabase = (): IMemoryDb => {

    db = newDb({
        autoCreateForeignKeyIndices: true
    })
    
    db.public.registerFunction({
    implementation: () => '13.0',
    name: 'version'
    })

    db.public.registerFunction({
    implementation: () => 'pg-mem',
    name: 'current_database'
    })

    db.public.registerFunction({
        name: 'obj_description',
        args: [DataType.text, DataType.text],
        returns: DataType.text,
        implementation: () => 'test'
    })
    
    return db
}

export const createPostgresDataSourceTest = async (): Promise<DataSource> => {
    const database = initDatabase()
  
    const dataSource: DataSource = await database.adapters.createTypeormDataSource({
      ...dbConfig,
      logger: 'advanced-console',
      logging: false
    })
  
    await dataSource.initialize()
    await dataSource.synchronize()
    return dataSource
}