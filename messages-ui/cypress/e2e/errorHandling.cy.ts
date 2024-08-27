/// <reference types="cypress" />
//Tests for running with dev API on
describe("template spec", () => {
  it("Should handle off api on submit message", () => {
    cy.intercept("POST", "http://localhost:8080/messages", {
      statusCode: 500,
      body: "Failed to send message",
    });
    cy.visit("http://localhost:3000");
    cy.get("a[href='/messages']").click();
    cy.get("select[name='category']").select("Sports");
    cy.get("textarea[name='message']").type("Hello, World!");
    cy.get("button[type='submit']").click();
    cy.on("window:alert", (str) => {
      expect(str).to.equal("Failed to send message, API could be off");
    });
  });
  it("Should handle request not completing for table ", () => {
    cy.intercept("GET", "http://localhost:8080/history", []);
    cy.visit("http://localhost:3000");

    cy.get("a[href='/log']").click();
    cy.wait(1000);
    cy.get("table").contains("Message");
  });
  it("Should handle empty values for table ", () => {
    cy.intercept("GET", "http://localhost:8080/history", {
      forceNetworkError: true,
    }).as("failed");
    cy.visit("http://localhost:3000");

    cy.get("a[href='/log']").click();
    cy.wait(5000);
    cy.get("div").contains("Error: Failed to fetch");
  });
});
