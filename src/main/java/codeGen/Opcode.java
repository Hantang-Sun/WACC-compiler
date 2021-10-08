package codeGen;

public enum Opcode {
  // Labeling sections of code
  LABEL,

  // Memory manipulation
  MOV,
  MOVEQ,
  MOVNE,
  MOVGE,
  MOVLT,
  MOVGT,
  MOVLE,
  PUSH,
  POP,
  STR,
  STRB,
  LDR,
  LDRSB,

  // Arithmetic
  SMULL,
  DIV,
  MOD,
  ADD,
  ADDS,
  SUB,
  SUBS,
  RSBS,
  LSL,
  LSR,

  //
  NEG,

  // Conditionals
  AND,
  ORR,
  NOT,
  EOR,

  // Branching
  CMP,
  B,
  BEQ,
  BNE,
  BGR,
  BGE,
  BLS,
  BLE,
  BLNE,

  BL,
  BLVS
}
