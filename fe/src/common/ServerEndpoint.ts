const prefix = '/api';

type AuthEndpoint = '/auth/token' | '/auth/login';





type Endpoint = `${typeof prefix}${AuthEndpoint}`;


export default Endpoint;