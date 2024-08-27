/// <reference types="cypress" />
//Tests for running with dev API on
describe("template spec", () => {
  it("Should send message", () => {
    cy.visit("http://localhost:3000");
    cy.get("a[href='/messages']").click();
    cy.get("select[name='category']").select("Sports");
    cy.get("textarea[name='message']").type("Hello, World!");
    cy.get("button[type='submit']").click();
    cy.on("window:alert", (str) => {
      expect(str).to.equal("Message sent");
    });
  });
  it("Should send message and appear on table", () => {
    cy.visit("http://localhost:3000");
    cy.get("a[href='/messages']").click();
    cy.get("select[name='category']").select("Finance");
    cy.get("textarea[name='message']").type("Im a new message");
    cy.get("button[type='submit']").click();
    cy.on("window:alert", (str) => {
      expect(str).to.equal("Message sent");
    });
    cy.get("a[href='/log']").click();
    cy.get("table").contains("Im a new message");
    cy.get("table")
      .contains("tr", "Finance")
      .then(($row) => {
        expect($row.index()).to.equal(0);
      });
  });
});
