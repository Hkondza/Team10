// Single Responsibility
class ApiClient {
  async post(url, body) {
    return fetch(url, {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify(body)
    });
  }
}

// Dependency Inversion
class AuthService {
  constructor(apiClient, storageAdapter) {
    this.api = apiClient;
    this.storage = storageAdapter;
  }

  async login(credentials) {
    const response = await this.api.post("/api/auth/login", credentials);
    if (!response.ok) {
      throw new Error("Invalid credentials");
    }
    const user = await response.json();
    this.storage.setItem("loggedUser", JSON.stringify(user));
    return user;
  }

  async register(data) {
    // Return the raw response so callers can decide flow
    return this.api.post("/api/auth/register", data);
  }
}

// Liskov
class StorageAdapter {
  getItem(_key) { throw new Error("Not implemented"); }
  setItem(_key, _value) { throw new Error("Not implemented"); }
  removeItem(_key) { throw new Error("Not implemented"); }
}

class LocalStorageAdapter extends StorageAdapter {
  constructor(storage = window.localStorage) {
    super();
    this.storage = storage;
  }
  getItem(key) { return this.storage.getItem(key); }
  setItem(key, value) { this.storage.setItem(key, value); }
  removeItem(key) { this.storage.removeItem(key); }
}

class MemoryStorageAdapter extends StorageAdapter {
  constructor() {
    super();
    this.map = new Map();
  }
  getItem(key) { return this.map.has(key) ? this.map.get(key) : null; }
  setItem(key, value) { this.map.set(key, value); }
  removeItem(key) { this.map.delete(key); }
}

// Open/Closed
class Validator {
  constructor() {
    this.rules = [];
  }
  addRule(fn) { this.rules.push(fn); }
  validate(data) {
    return this.rules
      .map(rule => rule(data))
      .filter(msg => typeof msg === "string" && msg.length > 0);
  }
}

// Interface Segregation
class EmailPasswordCredentials {
  constructor(email, password) {
    this.email = email;
    this.password = password;
  }
}

class RegistrationProfile {
  constructor(fullName, role) {
    this.fullName = fullName;
    this.role = role;
  }
}

// Expose to global namespace for simple script usage
window.JobFinder = {
  ApiClient,
  AuthService,
  StorageAdapter,
  LocalStorageAdapter,
  MemoryStorageAdapter,
  Validator,
  EmailPasswordCredentials,
  RegistrationProfile
};
