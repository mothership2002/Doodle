import {Container} from "inversify";

import {TYPES} from "./types";
import {Logger} from "../module/Logger";
import {ConnectionPool} from "../module/ConnectionPool";
import {MessageQueueConsumer} from "../module/MessageQueueConsumer";

const container = new Container();

container.bind<Logger>(TYPES.Logger).to(Logger).inSingletonScope();
container.bind<ConnectionPool>(TYPES.ConnectionPool).to(ConnectionPool).inSingletonScope();
container.bind<MessageQueueConsumer>(TYPES.MessageQueueConsumer).to(MessageQueueConsumer).inSingletonScope();

export default container;